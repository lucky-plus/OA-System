Ext.define('Admin.view.staff.StaffForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.staffForm',
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
	controller: 'StaffViewController',
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
		xtype: 'textfield',
		fieldLabel: 'userId',
		//allowBlank: false,
		name:'userId',

	},{
		xtype: 'textfield',
		fieldLabel: '姓名',
		name:'realName'
	},{
		xtype: 'datefield',
		format: 'Y/m/d H:i:s',
		fieldLabel: '入职时间',
		name:'onDutDate'
	},{
		xtype: 'textfield',
		fieldLabel: '联系电话',
		name:'mobilePhone'
    }],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'staffGridFromSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'staffGridWindowsClose'
		}]
    }
});
