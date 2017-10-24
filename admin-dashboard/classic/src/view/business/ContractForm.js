Ext.define('Admin.view.contract.ContractForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contractForm',
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
	controller: 'contractViewController',
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
		fieldLabel: 'contractId',
		//allowBlank: false,
		name:'contractId',
		//handler:'orderGridEdit'
	},{xtype:'hidden', 
		fieldLabel:'userId', 
		name:'userId', 
		value:loginUserId
	},{
		xtype:'textfield',
		fieldLabel: '合作单位',
		name:'company',
	},{
		xtype:'textfield',
		fieldLabel: '合同名称',
		name:'contractName',
	},{
		xtype:'textfield',
		fieldLabel: '合同编号',
		name:'contractNum',
	},{
		xtype:'datefield',
		fieldLabel: '日期',
		name:'contractDate',
	},{
		xtype: 'fileuploadfield',  
		fieldLabel: '合同照片（格式为：jpg/jpeg/png）',   
	},{
          xtype: 'hidden',
          fieldLabel: 'pictureFileName',
            name:'pictureFileName'
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
			handler: 'contractWindowsClose'
		}]
    }
});