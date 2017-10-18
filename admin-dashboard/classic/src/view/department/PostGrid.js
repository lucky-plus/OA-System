Ext.define('Admin.view.department.PostGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
	id:'postGrid',
    xtype: 'postGrid',
	bind:'{postLists}',
	deleteId:undefined,
	columns: [
		{sortable:false ,dataIndex:'postId',hidden:true},
		{text:'职位名称',sortable:false ,dataIndex:'postName' ,width:150},
		{text:'所属部门',sortable:false ,dataIndex:'deptName' ,width:150},
		{text:'职位描述',sortable:false ,dataIndex:'postDescribe' ,flex:1}
	],
	listeners:{
		cellclick:function(grid,td, cellIndex, record, tr, rowIndex){
			var record = grid.getStore().getAt(rowIndex);
			Ext.getCmp('postForm').getForm().loadRecord(record);
			grid.up('panel').deleteId=record;
		}
	}
});