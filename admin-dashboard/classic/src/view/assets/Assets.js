Ext.define('Admin.view.assets.Assets', {      //1.修改文件路径
    extend: 'Ext.container.Container',    //2.继承的组件类型
  //3.重写继承组件的属性：
  xtype: 'assets',

  controller: 'assetsViewController',      //视图绑定viewController
  viewModel : {type: 'assetsViewModel'},   //视图绑定viewModel


  layout:'fit',
  margin: '20 20 20 20',
  items: [{
      xtype: 'assetsGrid',
      assetsGrid:'assetsGrid'
  }]
});