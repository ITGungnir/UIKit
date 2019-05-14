# UIKit - BottomTab

`BottomTab`是一个底部导航栏控件，常用于首页底部，与ViewPager或Fragment连用。

`BottomTab`控件的特点有如下几点：
* 将`item`的绑定和渲染操作抽离控件，用户可以自定义`item`布局；
* 将`item`的切换事件提供给用户，用户可以自定义`item`状态效果；
* 控件仅控制`Fragment`的切换，其他属性和操作都由用户自定义，提升灵活度。

## 1、布局
```xml
<my.itgungnir.ui.bottom_tab.BottomTab
    android:id="@+id/bottomBar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="@color/colorPure"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent" />
```

## 2、使用
```kotlin
bottomBar.init(
    // targetFrameId：展示各个Fragment的控件的布局id
    targetFrameId = R.id.fragments,
    // fragmentManager：Fragment Manager 对象
    fragmentManager = supportFragmentManager,
    // items：item实体类，需自定义
    items = listOf(
        TabItem("Frag1", "\uE703", "\uE702") to ChildFragment1(),
        TabItem("Frag2", "\uE6EC", "\uE6EB") to ChildFragment2(),
        TabItem("Frag3", "\uE6EF", "\uE6EE") to ChildFragment3()
    ),
    // itemLayoutId：item的布局id
    itemLayoutId = R.layout.list_item_main_bottom_tab,
    // render：item的渲染回调，提供三个参数：view、data和selected(是否为选中状态)
    render = { view, data, selected ->
        val icon = view.findViewById<IconFontView>(R.id.iconView)
        val title = view.findViewById<TextView>(R.id.titleView)
        title.text = data.title
        when (selected) {
            true -> {
                icon.text = data.selectedIcon
                icon.textColor = selectedColor
                title.textColor = selectedColor
            }
            false -> {
                icon.text = data.unselectedIcon
                icon.textColor = unSelectedColor
                title.textColor = unSelectedColor
            }
        }
    }
)
```