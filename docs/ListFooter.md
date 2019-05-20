# UIKit - ListFooter

`ListFooter`用于为`RecyclerView`添加`加载更多`功能。

# 使用
```kotlin
private var footer: ListFooter? = null

footer = ListFooter.Builder()
    // 绑定到一个RecyclerView
    .bindTo(list)
    // 设置Footer的背景颜色和文本颜色
    .render(Color.BLACK, Color.WHITE)
    // 加载更多的回调
    .doOnLoadMore { }
    .build()
```
上述代码是对`ListFooter`进行初始化的代码。`ListFooter`中提供了三个方法，用于对其状态进行切换：
```kotlin
// 显示文本为“正在加载...”
footer?.onLoading()
// 显示文本取决于hasMore的值，hasMore为true时显示为“加载成功”，否则显示为“没有更多数据了”
footer?.onLoadSucceed(hasMore)
// 显示文本为“加载失败”
footer?.onLoadFailed()
```