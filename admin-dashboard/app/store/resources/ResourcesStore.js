Ext.define('Admin.store.resources.ResourcesStore', {
    extend: 'Ext.data.Store',
    alias: 'store.resourcesStore',			  //1.Store取别名（reference）
    data:{
		'items':[
			{ 'resourcesId': 1, 'resourcesName': '资料1',  "resourcesTime":"2017-7-13"},
			{ 'resourcesId': 2, 'resourcesName': '资料2',  "resourcesTime":"2017-7-13"},
			{ 'resourcesId': 3, 'resourcesName': '资料3',  "resourcesTime":"2017-7-13"}
		]
	},
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			root: 'items'
		}
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'id'
    }
});