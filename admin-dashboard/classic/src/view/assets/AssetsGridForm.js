Ext.define('Admin.view.assets.AssetsGridForm', {
  extend: 'Ext.form.Panel',
  alias: 'widget.assetsGridForm',
	//id:'assetsGridForm',//Ext.getCmp('assetsGridForm');
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
    controller: 'assetsViewController',
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
    items: [
    {
      xtype: 'hidden',
      fieldLabel: 'AssetsId',
		//allowBlank: false,
		name:'assetsId'
	},
	{
		xtype: 'textfield',
		fieldLabel: '资产编号',
		name:'assetsNumber'
	},
	{
		xtype: 'textfield',
		fieldLabel: '资产名称',
		name:'assetsName'
	},{
		xtype: 'datefield',
		format: 'Y/m/d H:i:s',
		fieldLabel: '开始使用时间',
		name:'assetsUsedTime'
	},{
		xtype: 'combobox',
		fieldLabel: '资产类型',
		name:'assetsType',
		store:  Ext.create('Ext.data.Store', {
			fields: ['value', 'name'],
			data : [
      {"value":"电子产品", 	    "name":"电子产品"},
      {"value":"办公用具",     "name":"办公用具"},
      {"value":"基本设备", 	    "name":"基本设备"},
      {"value":"交通工具", 	"name":"交通工具"}
      ]
    }),
		queryMode: 	  'local',
		displayField: 'name',
		valueField:   'value',
		value:'电子产品'
	},{
		xtype: 'textfield',
		fieldLabel: '资产估价',
		name:'assetsPrice'
 },{
        xtype: 'combobox',
        fieldLabel: '拥有资产者',
        name:'userId',
        id: 'taskcombobox',
        store :
        new Ext.data.Store( {
          proxy : new Ext.data.HttpProxy( {
              url : 'staff/findAllTaskUser.json' //提交到某action的某方法
            }),
        reader : {type:'json'},//需要显示的数据实体字段
        autoLoad : true
        }),
        queryMode:    'local',
        displayField: 'realName',
        valueField:   'userId',
        listConfig : {//设置下拉时显示的样式
          maxHeight : 170,//下拉时最大高度
          getInnerTpl : function() {
             return '<div data-qtip="{userId}">{realName}</div>';//这里面的id和name是你的store里面的属性
          }
        }
    }
  ],
  bbar: {
    overflowHandler: 'menu',
    items: ['->',{
     xtype: 'button',
     text: '提交',
     handler: 'assetsGridFormSubmit'
   },{
     xtype: 'button',
     text: '取消',
     handler: 'assetsGridWindowClose'
   }]
 }
});
