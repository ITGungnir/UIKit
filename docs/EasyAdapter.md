# UIKit - EasyAdapter

`EasyAdapter`是对`RecyclerView`的`Adapter`的封装，支持`多viewType`、`局部刷新`等。

`EasyAdapter`的适用场景包括：
* 绑定到`RecyclerView`上作为简单的列表适配器；
* 可以滚动的、多`viewType`的长列表页；
* 需要进行局部刷新的列表页。

## 1、绑定RecyclerView
`EasyAdapter`通过以下方法将自己绑定到`RecyclerView`上：
```kotlin
private var listAdapter: EasyAdapter? = null

// bind()方法用来设置LayoutManager和Differ
listAdapter = list.bind(
    diffAnalyzer = object : Differ {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
            if (oldItem is ChildState.BannerVO && newItem is ChildState.BannerVO) {
                true
            } else if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO) {
                oldItem.id == newItem.id
            } else false
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
            if (oldItem is ChildState.BannerVO && newItem is ChildState.BannerVO) {
                true
            } else if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO) {
                oldItem.text == newItem.text
            } else false
        override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Bundle? {
            val bundle = Bundle()
            if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO && oldItem.text != newItem.text) {
                bundle.putString("PL_TEXT", newItem.text)
            }
            return if (bundle.isEmpty) null else bundle
        }
    })
    // 添加Delegate，每个Delegate代表一种ViewType
    .addDelegate({ data -> data is ChildState.BannerVO }, BannerDelegate())
    .addDelegate({ data -> data is ChildState.TextVO }, TextDelegate())
    // 完成最后的初始化工作
    .initialize()
```
`addDelegate()`方法中有两个参数，第一个参数是判定条件，即符合特定条件的数据才能展示为特定的`Delegate`；第二个参数是具体的`Delegate`对象。

## 2、Differ
`Differ`是对`DiffUtil.DiffCallback`的封装，使之可以用于`EasyAdapter`中数据的局部更新判定。

对`Differ`中三个抽象方法的解释如下：
```kotlin
interface Differ {

    // 判断两个item是否是同一个item，即是否可以使用旧的item展示数据
    fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean

    // 判断同一个item中的数据是否发生了变化，即是否可以保留原布局而不需更新
    fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean

    // 通过比较新旧item数据，得到一个Bundle类型的更新包，通过这个包来局部更新item中的数据
    fun getChangePayload(oldItem: ListItem, newItem: ListItem): Bundle?
}
```
`Differ`的工作流程如下：
* 调用`areItemsTheSame()`方法比较两条数据，如果返回为`false`，则直接新建一个`item`展示数据，否则进入下一步；
* 调用`areContentsTheSame()`方法判断是否可以不更新布局，如果返回为`true`，则表示不需要局部更新，否则进入下一步；
* 如果代码运行到了这里，则说明该item中的数据发生了局部更改，需要进行局部更新，进入`getChangePayload()`方法；
* 在`getChangePayload()`方法中，对于`oldItem`和`newItem`，找到其中需要展示的、更新了的数据，放到一个`Bundle`对象中；
* 在`Delegate`的`onBindVH()`方法中可以判断`payload`是否为空，从而从中取出数据进行局部更新。

## 3、Delegate
一种`Delegate`表示一种`viewType`的`item`。`Delegate`类都需要继承自`BaseDelegate`类，且需要提供一种继承自`ListItem`接口的数据类作为泛型：
```kotlin
class TextDelegate : BaseDelegate<ChildState.TextVO>() {

    // 本Delegate对应的布局
    override fun layoutId(): Int = R.layout.list_item_text

    // 在Delegate创建时调用的代码
    override fun onCreateVH(container: View) {
        container.apply {
            // ... your codes
        }
    }

    // 在Delegate被展示时调用的代码
    override fun onBindVH(
        item: ChildState.TextVO,
        holder: EasyAdapter.VH,
        position: Int,
        payloads: MutableList<Bundle>
    ) {
        // ... your codes
    }
}
```
```kotlin
data class ChildState(/*...*/) : State {

    // ... Other codes

    data class TextVO(
        val id: Int,
        val text: String
    ) : ListItem
}
```
在`Delegate`子类的`onCreateVH()`方法中，通过`container.apply {}`的方式对布局进行初始化：
```kotlin
container.apply {
    menuIcon.textColor = menuIconColor
    menuTitle.textColor = menuTitleColor
}
```
在`Delegate`子类的`onBindVH()`方法中，通过`holder.render(item) {}`的方式对布局进行渲染，此时可以使用`payloads`中的数据进行局部更新：
```kotlin
holder.render(item) {
    nameView.text = if (payloads.isNotEmpty()) {
        val payload = payloads[0]
        payload.getString("PL_TEXT") ?: item.text
    } else {
        item.text
    }
}
```
**注意：** 这里解释一下局部更新和非局部更新的不同：当`RecyclerView`中使用了`DiffUtil`之后，再对`item`中的数据进行更新时，会发现`item`上有一个“白光一闪”的动画，
这种动画是调用`notifyDataSetChanged()`方法时不会出现的，它表明此`item`整体或其中的某个部分发生了改变，但由于没有做局部更新，因此看到的效果就是整个`item`都进行了刷新。
为了避免在更改`item`的某一部分时触发`item`整体的刷新，需要使用`DiffUtil`进行判断，并通过`payload`进行局部更新。局部更新只会在其作用的控件上执行“白光一闪”的动画，
因此便不再影响到`item`整体。

## 4、数据刷新
当数据集发生了改变时，可以通过以下代码，方便的更新`RecyclerView`中的数据：
```kotlin
listAdapter?.update(states.a)
```