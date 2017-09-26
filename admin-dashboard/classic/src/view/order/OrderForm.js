Ext.define('Admin.view.order.OrderForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.orderForm',
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
	controller: 'OrderViewController',
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
		name:'id',
		handler:'orderGridEdit'
	},{
		xtype: 'textfield',
		fieldLabel: '订单编号',
		name:'orderNumber'
	},{
		xtype: 'datefield',
		format: 'Y/m/d H:i:s',
		fieldLabel: '创建时间',
		name:'createTime'
	},{
		xtype: 'combobox',
		fieldLabel: '优先级',
		name:'level',
		store:  Ext.create('Ext.data.Store', {
			fields: ['value', 'name'],
			data : [
				{"value":"HIGH", 	"name":"高"},
				{"value":"MEDIUM",  "name":"中"},
				{"value":"LOW", 	"name":"低"}
			]
		}),
		queryMode: 	  'local',
		displayField: 'name',
		valueField:   'value'
	},{
		xtype: 'textfield',
		fieldLabel: 'Price',
		name:'price'
    }],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'orderGridFromSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'orderGridWindowsClose'
		}]
    }
});
