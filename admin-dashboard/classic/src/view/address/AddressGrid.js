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
		{text: '所属部门'  ,dataIndex:'deptName'  ,width:188},
		{text: '职位'  ,dataIndex:'postName'  ,width:188},
		{text: '联系电话'  ,dataIndex:'mobilePhone'  ,width:188},
		{text: '联系邮箱'  ,dataIndex:'mail'  ,width:188},
		{text: 'QQ'  ,dataIndex:'qq_number'  ,width:188},

	],		

	tbar: Ext.create('Ext.Toolbar', {
			items:[{xtype:'tbtext',
				text:'姓名：'
			},{
				xtype:'textfield',
				width:200,
				reference: 'addressGridSearchText'
			},{xtype:'tbtext',
				text:'所属部门'
			},{
			xtype: 'combobox',
			name:'deptId',
			reference: 'addressGridSearchField',
			store : 
			new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
							url : 'dept/findDepts'//提交到某action的某方法
					  }),
				reader : {type:'json'},//需要显示的数据实体字段
				autoLoad : true
				}),
			queryMode: 	  'local',
			displayField: 'deptName',
			valueField:   'deptId'
			
			},{
				text:'查找',
				listeners: {
					click: 'addressGridSearch'//快捷查询按钮
				}
			}]
	}),
	
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{addressLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});