# UIKit - Browser

`WebBrowser`是一个带进度条的网页加载和展示控件。

## 1、布局
```xml
<my.itgungnir.ui.browser.WebBrowser
    android:id="@+id/browser"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## 2、使用
```kotlin
// load()：设置要加载的网页URL
browser.load("https://www.baidu.com/")
    // onError()：加载网页失败时的回调方法，提供错误码code和错误信息msg
    .onError { code, msg ->
        statusView.failed { view ->
            view.findViewById<TextView>(R.id.errorMsg).text = "$code：$msg"
            view.findViewById<ProgressButton>(R.id.reload).ready("重新加载")
        }
    }
```
**注意：** `WebBrowser`中进度条的颜色取决于系统配置的`colorAccent`颜色值。

`WebBrowser`的`load()`方法支持两个参数，第二个参数可以指明是否支持无图模式，设置为`true`时，将不会加载页面中的图片：
```kotlin
browser.load("https://www.baidu.com/", true)
```