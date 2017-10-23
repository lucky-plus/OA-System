Ext.define('Admin.view.assets.AssetsSearchWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.assetsSearchWindow',
	autoShow: true,
	modal: true,
	layout: 'fit',
	width: 200,
	height: 200,
	controller: 'assetsViewController',
	title:'查询资产信息',
    	items:[{
		xtype:'form',
		//id:'userAddForm',
		layout: {type:'vbox',align:'stretch'},
		bodyPadding: 20,
		scrollable: true,
		defaults: {
			labelWidth: 100,
			labelSeparator: ''
		},
             defaultType: 'textfield',
		items:[
		{
			name: 'assetsName',
			fieldLabel: '资产名称',
			reference: 'assetsSearchForm-assetsName'
		},{
			xtype: 'datefield',
			fieldLabel: '开始时间',
			name: 'beginDate',
			itemId: 'startdt',
            endDateField: 'enddt',
			format: 'Y/m/d H:i:s',
			editable:false,//禁止手工修改
			value:new Date(),
			reference: 'assetsSearchForm-beginDate'
		},{
			xtype: 'datefield',
			fieldLabel: '结束时间',
			name: 'endDate',
			format: 'Y/m/d H:i:s',
			itemId: 'enddt',
			editable:false,//禁止手工修改
			value:Ext.util.Format.date(Ext.Date.add(new Date(),Ext.Date.MONTH,1),"Y/m/d H:i:s"),
			reference: 'assetsSearchForm-endDate'
		},{
			fieldLabel: '起始价格',
			name: 'lowPrice',
			reference: 'assetsSearchForm-lowPrice'
		},{
			fieldLabel: '结束价格',
			name: 'highPrice',
			reference: 'assetsSearchForm-highPrice'
		},{
			xtype: 'combobox',
			name: 'assetsType',
			fieldLabel: '资产类型',
			store: Ext.create('Ext.data.Store', {
			    fields: ['value', 'name'],
			    data : [
			    {"value":"电子产品", 	    "name":"电子产品"},
				{"value":"办公用具",     "name":"办公用具"},
				{"value":"基本设备", 	    "name":"基本设备"},
				{"value":"交通工具", 	"name":"交通工具"}
			    ]
			}),
			//emptyText : '请选择...',
		    mode : 'local',// 数据模式，local代表本地数据
			editable : false,// 是否允许输入
			allowBlank : false,// 不允许为空
		    //readOnly : true,// 是否只读
			queryMode: 'local',
			valueField: 'value',
            displayField: 'name',
            reference: 'assetsSearchForm-assetsType'
            //value : '电子产品'// 默认值,要设置为提交给后台的值，不要设置为显示文本,可选
		}
		],
		buttons: [{
            text: 'Search',
            handler: 'assetsSearchFormSubmit'
        },{
            text: 'Cancel',
            handler: function() {
                this.up('form').getForm().reset();
                this.up('window').hide();
            }
        }]
	}],
	afterRender: function () {
	    var me = this;
	    me.callParent(arguments);
	    me.syncSize();
	    Ext.on(me.resizeListeners = {
	        resize: me.onViewportResize,
	        scope: me,
	        buffer: 50
	    });
	},
	doDestroy: function () {
	    Ext.un(this.resizeListeners);
	    this.callParent();
	},
	onViewportResize: function () {
	    this.syncSize();
	},
	syncSize: function () {
        var width = Ext.Element.getViewportWidth(),
            height = Ext.Element.getViewportHeight();
        this.setSize(Math.floor(width * 0.5), Math.floor(height * 0.5));
        this.setXY([ Math.floor(width * 0.05), Math.floor(height * 0.05) ]);
    }
});