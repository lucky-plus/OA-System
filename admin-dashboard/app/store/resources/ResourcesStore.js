Ext.define('Admin.store.resources.ResourcesStore', {
    extend: 'Ext.data.Store',
    alias: 'store.resourcesStore',			  //1.Store取别名（reference）
	proxy: {
		type: 'ajax',
		url: 'resources/findPage.json',
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'
		},
		simpleSortMode: true
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'resId'
    }
});