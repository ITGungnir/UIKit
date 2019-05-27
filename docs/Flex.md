# UIKit - Flex

`Flex`是`UIKit`中对`FlexLayout`的相关支持的封装，主要提供了两个控件：`FlexView`和`ScrollableFlexView`。

由于`FlexView`继承了`FlexboxLayout`控件，因此，在使用`UIKit`库时，需要额外导入`flexbox`包的依赖，否则不能运行：
```groovy
implementation "com.google.android:flexbox:$flex_version"
```

**注意：** 目前，`UIKit`中的两个`Flex`相关控件，都是仅支持`水平的`、`自动换行的`布局方式！

## 1、FlexView
`FlexView`直接继承自谷歌开源的`FlexboxLayout`控件，不能滚动，不处理用户的手势事件，可以用于高度较小的`Flex`控件组的展示，或者嵌入到列表中的`Flex`控件组的展示：
```xml
<app.itgungnir.kwa.common.widget.flex.FlexView
    android:id="@+id/childrenView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="10dp"
    android:layout_marginTop="10dp"
    android:layout_marginEnd="10dp"
    android:layout_marginBottom="10dp"
    app:sfv_horizontalSpacing="20dp"
    app:sfv_verticalSpacing="5dp" />
```
`FlexView`的使用分为`bind`和`refresh`两个步骤：
```kotlin
// bind()方法应在FlexView初始加载时回调，建议放到onCreateView()等方法中执行
childrenView.bind<TreeState.TreeVO.TreeTagVO>(
    // layoutId：单个item的布局
    layoutId = R.layout.list_item_tree_child,
    // render：单个item的渲染回调
    render = { view, data ->
        view.findViewById<TextView>(R.id.nameView).text = data.name
    }
)
```
```kotlin
// refresh()方法应在onBindView()等方法中执行
childrenView.refresh(item.children)
```
`FlexView`中提供了以下两个自定义属性，用来设置横向和纵向item之间的间隔：

|名称|含义|默认值|
|---|---|---|
|app:fv_horizontalSpacing|item之间的横向间隔|10dp|
|app:fv_verticalSpacing|item之间的纵向间隔|10dp|

## 2、ScrollableFlexView
`ScrollableFlexView`与`FlexView`类似，但可以滚动，其用法与`FlexView`完全相同。

`ScrollableFlexView`中也提供了设置横向和纵向item之间的间隔的属性：

|名称|含义|默认值|
|---|---|---|
|app:sfv_horizontalSpacing|item之间的横向间隔|10dp|
|app:sfv_verticalSpacing|item之间的纵向间隔|10dp|