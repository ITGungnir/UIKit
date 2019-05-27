# UIKit - Browser

`WebBrowser`是一个带进度条的网页加载和展示控件，支持`错误回调`、`页内回退`、`阻绝图片加载`等功能。

## 0、准备
如果要使用`WebBrowser`控件，需要先在项目的`AndroidManifest.xml`文件中添加权限：
```xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <!-- Your codes -->
</manifest>
```

## 1、布局
```xml
<my.itgungnir.ui.browser.WebBrowser
    android:id="@+id/browserView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## 2、使用
### （1）加载网页
```kotlin
browserView.load(
    // url：要加载的网页URL
    url = "https://bugly.qq.com/v2/index",
    // blockImage：是否要阻绝图片加载，置为true时将不加载网页中的图片
    blockImage = false,
    // indicatorColor：进度条的颜色
    indicatorColor = Color.YELLOW,
    // errorLayoutId：当页面发生错误时，显示的布局
    errorLayoutId = R.layout.status_view_error,
    // errorCallback：对错误布局的渲染
    errorCallback = {
        it.findViewById<TextView>(R.id.errorMsg).text = "页面加载时出现问题，请重试~"
        it.findViewById<ProgressButton>(R.id.reload).ready("重新加载")
    }
)
```

### （2）页内回退
`WebBrowser`控件提供了一个`goBack()`方法，支持页内回退，当该方法返回为`true`时，表示前面仍有其他页面可以回退，否则表示当前页已位于栈底。示例代码如下：
```kotlin
override fun onBackPressed() {
    if (!browserView.goBack()) {
        super.onBackPressed()
    }
}
```

### （3）当前信息
`WebBrowser`提供了两个方法用于获取当前页面的信息：
```kotlin
browserView.currentTitle() // 获取当前正在访问的页面的标题
browserView.currentUrl()   // 获取当前正在访问的页面的URL
```