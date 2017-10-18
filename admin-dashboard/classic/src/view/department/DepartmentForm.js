Ext.define('Admin.view.department.DepartmentForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.deptForm',
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
	controller: 'departmentViewController',
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
    items: [
	{
		xtype: 'hidden',
		fieldLabel: 'deptId',
			//allowBlank: false,
		name:'deptId',
		handler:'deptGridOpenEditWindow'
	},
	{
		xtype: 'combobox',
		fieldLabel: '上级部门',
		name:'parentId',			
		store:  new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
							url : 'dept/findDepts'//提交到某action的某方法
					  }),
				reader : {type:'json'},//需要显示的数据实体字段
				autoLoad : true
				}),
		listeners:{
			render:function(){
				this.store.reload();

		}},
		listConfig : {//设置下拉时显示的样式
			maxHeight : 200,//下拉时最大高度
		},
		queryMode: 	  'local',
		displayField: 'deptName',
		valueField:   'deptId',
	},{
		xtype: 'textfield',
		fieldLabel: '部门名称',
		name:'deptName'
    }],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'deptGridFormSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'deptGridWindowClose'
		}]
    }
});