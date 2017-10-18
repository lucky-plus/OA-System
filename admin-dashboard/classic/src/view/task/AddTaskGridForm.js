Ext.define('Admin.view.role.AddTaskGridForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.addTaskGridForm',
	id:'addTaskGridForm',//Ext.getCmp('roleGridForm');
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
	controller: 'taskViewController',
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
		fieldLabel: 'createId',
		name:'createId',
		value: loginUserId
	},{
		xtype: 'hidden',
		fieldLabel: 'createName',
		name:'createName',
		value: loginUserRealName
	},{
		xtype: 'textfield',
		fieldLabel: '任务名称',
		name:'taskName'
	},{
      	xtype: 'combobox',
      	fieldLabel: '接收者',
      	name:'userId',
      	id: 'taskcombobox',
      	store : 
      	new Ext.data.Store( {
        	proxy : new Ext.data.HttpProxy( {
              url : 'staff/findTaskUser.json?roleLevel='+loginUserRoleLevel //提交到某action的某方法
            }),
        reader : {type:'json'},//需要显示的数据实体字段
        autoLoad : true
        }),
      	queryMode:    'local',
      	displayField: 'realName',
      	valueField:   'userId'
  	},{
        xtype: 'htmleditor',
        buttonDefaults: {
            tooltip: {
                align: 't-b',
                anchor: true
            }
        },
        flex: 1,
        minHeight: 100,
        labelAlign: 'top',
        fieldLabel: '任务内容：',
		fontFamilies: ["宋体", "隶书", "黑体"],
		name:'taskText'
    },{
		xtype: 'hidden',
		fieldLabel: 'taskState',
		name:'taskState',
	}],

    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			//ui: 'soft-red',
			text: '提交',
			handler: 'taskGridFormSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'taskGridWindowClose'
		}]
    }
});