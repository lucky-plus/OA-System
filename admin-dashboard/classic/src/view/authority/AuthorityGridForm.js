Ext.define('Admin.view.authority.AuthorityGridForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.authorityGridForm',
	//id:'orderGridForm',//Ext.getCmp('orderGridForm');
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
	controller: 'AuthorityViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 60,
        labelSeparator: ''
    },
    items: [{
		xtype: 'hidden',
		fieldLabel: 'userId',
		//allowBlank: false,
		name:'userId'
	},{
		xtype: 'textfield',
		fieldLabel: '用户名称',
		name:'userName'
	},
	{
		xtype: 'radiogroup',
		fieldLabel: '角色名称',
		columns: 5,
		vertical: true,
		items: [
			{ boxLabel: 'r1',   name: 'roleId', inputValue: '1' },
			{ boxLabel: 'r2', name: 'roleId', inputValue: '2' },
			{ boxLabel: 'r3', name: 'roleId', inputValue: '3' },
			{ boxLabel: 'r4',   name: 'roleId', inputValue: '4' }
		]
	}
	],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			//ui: 'soft-red',
			text: '提交',
			handler: 'authorityGridFormSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'authorityGridWindowClose'
		}]
    }
});