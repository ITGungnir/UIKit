# UIKit - ListFooter

`ListFooter`用于为`RecyclerView`添加`加载更多`功能，现已支持自定义布局。

# 使用
第一步，自定义一个`Footer`的布局，如下是`view_list_footer.xml`文件中的代码：
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_margin="10dp"
    android:background="@color/colorPrimary">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="@string/app_name"
        android:textColor="@color/colorPure"
        android:textSize="@dimen/ui_kit_sp_text_size_2"
        android:textStyle="bold"
        tools:ignore="SpUsage" />

    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:layout_centerInParent="true" />

</RelativeLayout>
```
第二步，初始化`ListFooter`，使用构造者模式，其中可以通过`render()`方法置顶底栏的布局ID和对不同状态的响应：
```kotlin
private var footer: ListFooter? = null

footer = ListFooter.Builder()
    // 绑定到一个RecyclerView
    .bindTo(list)
    // 设置Footer的布局ID，以及在不同状态下的展示效果
    .render(R.layout.view_list_footer) { view, status ->
        val title = view.findViewById<TextView>(R.id.title)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        when (status) {
            FooterStatus.Status.PROGRESSING -> {
                title.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            FooterStatus.Status.SUCCEED -> {
                title.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                title.text = "加载成功，还有更多哦~"
            }
            FooterStatus.Status.NO_MORE -> {
                title.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                title.text = "加载成功，但我是有底线的"
            }
            FooterStatus.Status.FAILED -> {
                title.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                title.text = "啊嘞？怎么出错了！？"
            }
        }
    }
    // 加载更多的回调
    .doOnLoadMore {
        // Your code
    }
    .build()
```
**注意：** 此处如果不调用`render()`方法，将使用默认的`view_uikit_list_footer.xml`作为底栏的布局，并显示默认的文本。

第三步，通过`ListFooter`中提供的三个方法对其状态进行切换：
```kotlin
// 显示文本为“正在加载...”
footer?.onLoading()
// 显示文本取决于hasMore的值，hasMore为true时显示为“加载成功”，否则显示为“没有更多数据了”
footer?.onLoadSucceed(hasMore)
// 显示文本为“加载失败”
footer?.onLoadFailed()
```