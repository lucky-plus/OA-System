Ext.define('Admin.store.notice.NoticeStore', {
    extend: 'Ext.data.Store',
    alias: 'store.noticeStore',			  //1.Store取别名（reference）
	model: 'Admin.model.notice.NoticeModel',
	proxy: {
		type: 'ajax',
		url: 'notice/findPage.json',
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
        property: 'noticeId'
    }
});