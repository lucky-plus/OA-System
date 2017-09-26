/**
*订单模块子视图
*/
Ext.define('Admin.view.order.OrderGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'orderGrid',
	title:'<b>订单列表</b>',
	bind:'{orderLists}',
	id:'orderGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'ID'			  ,sortable:true ,dataIndex:'id',hidden:true},
        {text: '订单编号' ,sortable:true ,dataIndex:'orderNumber' ,width:120},
		{text: '创建时间'  ,sortable:true ,dataIndex:'createTime'  ,width:125
			,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '优先级',sortable:true ,dataIndex:'level'    ,width:125},
		{text: '价格'		  ,sortable:true ,dataIndex:'price' ,flex:1}
	],	


	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
			text: 'Add',
			iconCls:'x-fa fa-plus',
			ui:'soft-blue',
			//handler: 'orderGridOnClick'
			listeners:{
				click:'orderGridOnClick'
			}
		},'-', {
			text: 'Edit',
			iconCls:'x-fa fa-edit',
			handler: 'orderGridEdit'
		},'-', {
			text: 'Delete',
			iconCls:'x-fa fa-trash',
			handler: 'orderGridDelete'
		}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{orderLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});