Ext.define('Admin.view.notice.NoticeViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.NoticeViewController',

    noticeGridAdd: function(bt) {
		//alert("Add Wiondws");
		var cfg = Ext.apply({
			xtype:'orderWindow'
		},{
			title:'新建公告',
			items:[Ext.apply({xtype:'noticeCompose'})]
		});
		Ext.create(cfg);
    },
})
	