Ext.define('Admin.view.task.TaskGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'taskGrid',
	title:'<b>日志记录</b>',
	//bind:'{taskLists}',
	id:'taskGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'taskId'	,sortable:false ,dataIndex:'taskId',hidden:true},
		{text: '任务名称', sortable:false ,dataIndex:'taskName' ,width:100},
		{text: '任务发布时间' ,sortable:true ,dataIndex:'createDate' ,width:100,
		 renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '接收者', sortable:false ,dataIndex:'userName' ,width:100},
		{text: '发布者', sortable:false ,dataIndex:'createName' ,width:100},
		{text: '状态'  ,sortable:false ,dataIndex:'taskState'  ,flex:1}
	],	


	tbar: Ext.create('Ext.Toolbar', {
			id: 'taskCondition',
			// items:[
			// {
			// 	items:[ {
			// 		text: '发布任务',
			// 		id: 'taskAddButton',
			// 		iconCls:'x-fa fa-plus',
			// 		ui:'soft-blue',
			// 		//handler: 'roleGridAdd'
			// 		listeners:{
			// 			click:'taskGridAdd'
			// 		}
			// 	},'-', {
			// 		text: '修改',
			// 		id: 'taskUpdateButton',
			// 		iconCls:'x-fa fa-edit',
			// 		handler: 'taskGridEdit'
			// 	},'-', {
			// 		text: '删除',
			// 		id: 'taskDeleteButton',
			// 		iconCls:'x-fa fa-trash',
			// 		handler: 'taskGridDelete'
			// 	}]
			// },{
				items:[ {xtype:'tbtext',
					text:'接收者：'
				},{
					xtype:'textfield',
					width:100,
					itemsId:'userName'
					
				},{xtype:'tbtext',
					text:'发布者：'
				},{
					xtype:'textfield',
					width:100,
					itemsId:'createName'
					
				},{xtype:'tbtext',
					text:'状态：'
				},{
					xtype:'textfield',
					width:100,
					itemsId:'taskState'
					
				},{xtype:'tbtext',
					text:'发布时间：'
				},{
					 xtype:'datefield',  
	                    itemId:'beginDate',  
	                    format:'Y-m-d',  
						value:'2017-01-01'
						
				
				},{xtype:'tbtext',
					text:'至：'
				},{
					xtype:'datefield',  
	                    itemId:'endDate',  
	                    format:'Y-m-d',  
						value:new Date(),
						listeners: {  
						focus: function(){
							var cc = Ext.getCmp('taskCondition').items.getAt(7).getValue();
							this.setMinValue(cc);
							}  	
						}
				},{
					text: '查找',
					handler:'taskGridFind'
				}]
			// }
			// ]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{roleLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	})
	
});