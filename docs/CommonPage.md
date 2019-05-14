# UIKit - CommonPage

`CommonPage`是一个通用页面，常用于列表页的展示。

`CommonPage`的特点包括以下几点：
* 对`SwipeRefreshLayout`、`StatusView`、`FloatingActionButton`进行了封装；
* 点击`FAB`可以回到顶部；`FAB`的样式可以自定义；
* 默认提供了`支持下拉刷新`、`状态切换`和`回到顶部`等功能，使列表页面的开发更加简单高效。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:cp_fabBackColor|FAB的背景颜色|系统根据colorAccent支持的颜色|
|app:cp_fabRippleColor|FAB被按住时的水波纹颜色|系统根据colorAccent支持的颜色|
|app:cp_fabSrc|FAB中图标的src|svg_to_top|

## 2、布局
```xml
<my.itgungnir.ui.common_page.CommonPage
    android:id="@+id/childPage"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## 3、使用
```kotlin
// CommonPage初始化
childPage.apply {
    // Refresh Layout
    refreshLayout().setOnRefreshListener {
        viewModel.getDataList()
    }
    // Status View
    statusView().addDelegate(StatusView.Status.SUCCEED, R.layout.status_view_list) {
        val list = it.findViewById<RecyclerView>(R.id.list)
        // ... Other codes
    }
}
```
可以通过`childPage`提供的各种函数的调用来操作其中的不同子控件：
```kotlin
childPage.refreshLayout().isRefreshing = true

childPage.statusView().succeed { listAdapter?.update(states.a) }
```