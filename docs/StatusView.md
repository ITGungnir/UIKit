# UIKit - StatusView

`StatusView`是一个可以在不同状态下切换的控件，常用于网页、列表页等页面的加载。

`StatusView`提供了四种状态：
* LOADING：正在加载；
* SUCCEED：加载成功；
* EMPTY：数据集为空；
* FAILED：加载失败。

## 1、布局
```xml
<my.itgungnir.ui.status_view.StatusView
    android:id="@+id/statusView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## 2、使用
在初始化布局时，可以通过`addDelegate()`方法添加某个状态下的布局，并初始化其中的控件：
```kotlin
statusView.addDelegate(StatusView.Status.SUCCEED, R.layout.status_view_web) {
    loadSucceedPage(it)
}.addDelegate(StatusView.Status.FAILED, R.layout.status_view_error) {
    loadFailedPage(it)
}
```
当界面数据满足了某个状态的要求时，通过特定的方法切换状态，并通过回调设置相关的布局：
```kotlin
statusView.loading { view -> /* Your codes */ }

statusView.succeed { view -> /* Your codes */ }

statusView.empty { view -> /* Your codes */ }

statusView.failed { view -> /* Your codes */ }
```