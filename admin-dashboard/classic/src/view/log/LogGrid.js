/**
*订单模块子视图
*/
Ext.define('Admin.view.log.LogGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'logGrid',
	title:'<b>日志记录</b>',
	bind:'{logLists}',
	id:'logGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: '操作时间' ,sortable:true ,dataIndex:'createDate' ,width:150},
		{text: '操作类型'  ,sortable:true ,dataIndex:'operation'  ,width:80},
		{text: '操作人', sortable:true ,dataIndex:'userName' ,flex:80},
		{text: '具体操作', sortable:true ,dataIndex:'content' ,flex:1}
	],	


	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
			text: '添加角色',
			iconCls:'x-fa fa-plus',
			ui:'soft-blue',
			//handler: 'roleGridAdd'
			listeners:{
				click:'roleGridAdd'
			}
		},'-', {
			text: '修改',
			iconCls:'x-fa fa-edit',
			handler: 'roleGridEdit'
		},'-', {
			text: '删除',
			iconCls:'x-fa fa-trash',
			handler: 'roleGridDelete'
		}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{roleLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	})
	
});