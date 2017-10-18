Ext.define('Admin.view.department.PostForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.postForm',
	id:'postForm',
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
		fieldLabel: 'postId',
			//allowBlank: false,
		name:'postId',
		//handler:'deptGridOpenEditWindow'
	},
	{
		xtype: 'combobox',
		fieldLabel: '所属部门',
		name:'deptId',			
		store:  new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
							url : 'dept/findDepts'//提交到某action的某方法
					  }),
				reader : {type:'json'},//需要显示的数据实体字段
				autoLoad : true
				}),
		listeners:{
			select:function(){
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
		fieldLabel: '职位名称',
		name:'postName'
    },{
		xtype: 'textfield',
		fieldLabel: '职位描述',
		name:'postDescribe'
    }],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'postGridFormSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '删除',
			handler: 'postGridDeleteOne'
		}]
    }
});