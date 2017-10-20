Ext.define('Admin.store.assets.AssetsStore', {
  extend: 'Ext.data.Store',
    alias: 'store.assetsStore',			  //1.Store取别名（reference）
    model: 'Admin.model.assets.AssetsModel',//2.设置model的全路径
    proxy: {
      type: 'ajax',
		url: 'assets/findPage.json',	//后台AssetsController中的接口url地址
		reader: {
			type:'json',
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	//一共多少条记录的属性
		},
		simpleSortMode: true	//简单排序模式
	},
	pageSize: 15,
	autoLoad: true,
	remoteSort: true,//全局排序
  sorters: {
    direction: 'DESC',
    property: 'assetsId'
  }
});