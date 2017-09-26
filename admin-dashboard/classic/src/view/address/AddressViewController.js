Ext.define('Admin.view.address.AddressViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.addressViewController',

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