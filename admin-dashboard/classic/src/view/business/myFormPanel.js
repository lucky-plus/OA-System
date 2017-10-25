Ext.define('Admin.view.business.myFormPanel', {
    extend: 'Ext.container.Container',
    xtype: 'myFormPanel',

    layout: 'fit',
    
	// html : '表单提交'
    items:[{
            xtype: 'myFormGrid'
        }
    ]
});