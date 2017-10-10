Ext.define('Admin.view.resources.ResourcesForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.resourcesForm',
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor',
		'Ext.form.field.TextArea',
		'Ext.form.field.Time',
		'Ext.form.field.ComboBox',
		'Ext.form.field.Date',
		'Ext.form.field.Radio',
		'Ext.form.field.Hidden'
    ],
	
    //viewModel: {type: 'emailcompose'},
    //cls: 'email-compose',
	controller: 'resourcesViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 100,
        labelSeparator: ''
    },
    items: [{
		xtype: 'hidden',
		fieldLabel: 'Id',
		//allowBlank: false,
		name:'resId',
		//handler:'orderGridEdit'
	},{xtype:'hidden', 
		fieldLabel:'userId', 
		name:'userId', 
		value:loginUserId}
	,{
		xtype:'textfield',
		fieldLabel: '文件名称(限制50M以内)',
		//allowBlank: false,
		name:'resName',
		//handler:'orderGridEdit'
	},{
		xtype: 'fileuploadfield',  
		fieldLabel: '文件名',   
		
		


	}],

    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '上传',
			handler: 'fileUpload'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'orderGridWindowsClose'
		}]
    }
});