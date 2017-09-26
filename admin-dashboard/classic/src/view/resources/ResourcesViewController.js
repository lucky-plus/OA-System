Ext.define('Admin.view.resources.ResourcesViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.resourcesViewController',

    noticeGridAdd: function(bt) {
		//alert("Add Wiondws");
		var cfg = Ext.apply({
			xtype:'orderWindow'
		},{
			title:'上传资料',
			//items:[Ext.apply({xtype:'noticeCompose'})]
		});
		Ext.create(cfg);
    },
	
});