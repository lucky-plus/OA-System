Ext.define('Admin.view.staff.StaffGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'staffGrid',
	title:'<b>员工列表</b>',
	bind:'{staffLists}',
	id:'staffGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'ID'			  ,sortable:true ,dataIndex:'userId',hidden:true},
        {text: '职工姓名' ,sortable:true ,dataIndex:'realName' ,width:120},
		{text: '性别' ,sortable:true ,dataIndex:'sex' ,width:120},
		{text: '籍贯' ,sortable:true ,dataIndex:'nativePlace' ,width:120},
		{text: '出生时间'  ,sortable:true ,dataIndex:'birthday'  ,width:125
			,renderer: Ext.util.Format.dateRenderer('Y/m/d')},
		{text: '入职时间'  ,sortable:true ,dataIndex:'onDutDate'  ,width:125
			,renderer: Ext.util.Format.dateRenderer('Y/m/d')},
		{text: '所属部门',sortable:true ,dataIndex:'deptName' ,width:125},
		{text: '职位',sortable:true ,dataIndex:'postName' ,width:125},
		{text: '联系电话',sortable:true ,dataIndex:'mobilePhone' ,width:125},
		{xtype: 'actioncolumn',  text: '操作' ,flex:1,tdCls: 'action',  
            items: ['-',{  

				icon:'resources/images/icons/editor.png',
                tooltip: '编辑',
				handler: ('staffGridOpenEditWindow')
				
            },'-', {  
				icon:'resources/images/icons/delete.png',
                tooltip: '删除',
                handler: ('staffGridDeleteOne') 
            }]  }
	],	


	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
					text: '新建员工',
					iconCls:'x-fa fa-plus',
					ui:'soft-blue',
					//handler: 'staffGridOnClick'
					listeners:{
						click:'staffGridOnClick'
					}
				},'-', 
				{
					text: '批量删除',
					iconCls:'x-fa fa-trash',
					handler: 'staffGridDelete'
				},'-',
				{
					xtype:'tbtext',
					text:'姓名'
				},
				{
					xtype:'textfield',
					width:150,
					reference: 'staffGridSearchText'
				},
				{
					xtype:'tbtext',
					text:'部门'
				},{
					xtype: 'combobox',
					reference: 'staffGridSearchField',
					name:'deptId',
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
				handler: 'staffGridSearch'
				}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{staffLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});