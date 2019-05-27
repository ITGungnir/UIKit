# UIKit - Banner

`Banner`是一个可以自动无限循环滚动的控件，常用于应用首页中的广告栏中。

`Banner`控件的特点有如下几点：
* 可以自动无限循环滚动，可以设置其自动滚动的时间间隔；
* 支持自定义自动滚动和无限滚动；
* 与`indicator`解耦，用户可以灵活的自定义`indicator`；
* 提供了`item渲染`、`item点击`、`页面切换`的监听，使用更加灵活。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:b_scrollTimeSpan|自动滚动的时间间隔(s)|5|
|app:b_autoScroll|是否自动滚动|true|
|app:b_infiniteScroll|是否无限滚动|trie|

## 2、布局
```xml
<my.itgungnir.ui.banner.Banner
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:b_autoScroll="false"
    app:b_infiniteScroll="true"
    app:b_scrollTimeSpan="5" />
```

## 3、使用
`Banner`的使用分为两步，分别是`bind`和`update`。

`bind`只需调用依次即可，可以写在如`onCreateView()`等方法中：
```kotlin
// 泛型表示Banner中加载的数据的类型
banner.bind<Int>(
    // layoutId：banner item的布局文件
    layoutId = R.layout.list_item_banner_child,
    // render：单个item的渲染回调，参数有position、view和data
    render = { _, view, data ->
        view.findViewById<ImageView>(R.id.imageView).imageResource = data
    },
    // onClick：单个item的点击事件，参数有position和data
    onClick = { position, _ ->
        Toast.makeText(this.context, "Click on position $position", Toast.LENGTH_SHORT).show()
    },
    // onPageChange：切换到某个item时的回调，参数有position、totalCount和data
    onPageChange = { position, totalCount, data ->
        indicator.text = "${position + 1}/$totalCount"
    }
)
```

`update`可以调用多次，可以写在如`onBindView()`等方法中：
```kotlin
banner.update(item.imageResources)
```