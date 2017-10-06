Ext.define('Admin.view.address.AddressGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'addressGrid',
	title:'<b>通讯中心</b>',
	bind:'{addressLists}',
	id:'addressGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: '编号',dataIndex:'userId',hidden:true},
        {text: '联系人' ,dataIndex:'realName' ,flex:1 },
		{text: '所属部门'  ,dataIndex:'dept'  ,width:150},
		{text: '联系电话'  ,dataIndex:'mobilePhone'  ,width:150},
		{text: '联系邮箱'  ,dataIndex:'mail'  ,width:150},
		{text: 'QQ'  ,dataIndex:'qq_number'  ,width:150},

	],		

	tbar: Ext.create('Ext.Toolbar', {
			items:[{xtype:'tbtext',
				text:'姓名：'
			},{
				xtype:'textfield',
				width:200
			},{xtype:'tbtext',
				text:'所属部门：'
			},{
			xtype: 'combobox',
			name:'department',
			store:  Ext.create('Ext.data.Store', {
				fields: ['department', 'name'],
				data : [
					{"department":"HIGH", 	"name":"财务部"},
					{"department":"MEDIUM",  "name":"业务部"},
					{"department":"LOW", 	"name":"人事部"}
					]
				}),
				queryMode: 	  'local',
				displayField: 'name',
				valueField:   'value'
			
			},{
				text:'查找'
			}]
	}),
	
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{addressLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});