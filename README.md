# UIKit

`UIKit`是一个控件库，其中提供的控件及文档如下：
* [Banner](./docs/Banner.md)：一个可以自动无限循环滚动的控件
* [BottomTab](../docs/BottomTab.md)：一个底部导航栏控件
* [Browser](../docs/Browser.md)：一个带进度条的网页加载和展示控件
* [CommonPage](../docs/CommonPage.md)：一个通用页面
* [Dialog](../docs/Dialog.md)：自定义封装的对话框
* [EasyAdapter](../docs/EasyAdapter.md)：对`RecyclerView`的`Adapter`的封装，支持`多viewType`、`局部刷新`等
* [Flex](../docs/Flex.md)：对`FlexLayout`的相关支持的封装
* [HeadBar](../docs/HeadBar.md)：一个自定义封装的标题栏控件
* [IconFont](../docs/IconFont.md)：一个可以展示`IconFont`字体的控件
* [Input](../docs/Input.md)：一系列表单控件
* [ListFooter](../docs/ListFooter.md)：用于为`RecyclerView`添加`加载更多`功能
* [SearchBar](../docs/SearchBar.md)：一个搜索栏控件
* [StatusView](../docs/StatusView.md)：一个可以在不同状态下切换的控件

## 使用
在`build.gradle`文件中加入如下依赖：
```groovy
dependencies {
    // ... Your codes
    api "com.github.ITGungnir:UIKit:$uikit_version"
}
```
参考不同控件的相关文档，使用控件即可。
