Ext.define('Admin.view.log.LogGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'logGrid',
	title:'<b>日志记录</b>',
	bind:'{logLists}',
	id:'logGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: '操作时间' ,sortable:true ,dataIndex:'createDate' ,width:150,
		 renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '操作类型'  ,sortable:true ,dataIndex:'operation'  ,width:100},
		{text: '操作人用户名', sortable:true ,dataIndex:'userName' ,width:100},
		{text: '操作人姓名', sortable:true ,dataIndex:'realName' ,width:100},
		{text: '具体操作', sortable:true ,dataIndex:'content' ,flex:1}
	],	


	tbar: Ext.create('Ext.Toolbar', {
			id: 'logCondition',
			items:[ {xtype:'tbtext',
				text:'用户名：'
			},{
				xtype:'textfield',
				width:100,
				itemsId:'userName'
				
			},{xtype:'tbtext',
				text:'姓名：'
			},{
				xtype:'textfield',
				width:100,
				itemsId:'realName'
				
			},{xtype:'tbtext',
				text:'操作类型：'
			},{
				xtype:'textfield',
				width:100,
				itemsId:'operation'
				
			},{xtype:'tbtext',
				text:'时间：'
			},{
				 xtype:'datefield', 
				 editable:false,//禁止手工修改 
                    itemId:'beginDate',  
                    format:'Y-m-d',  
					value:'2017-01-01'
					
			
			},{xtype:'tbtext',
				text:'至：'
			},{
				xtype:'datefield',
				editable:false,//禁止手工修改 
                    itemId:'endDate',  
                    format:'Y-m-d',  
					value:new Date(),
					listeners: {  
					focus: function(){
						var cc = Ext.getCmp('logCondition').items.getAt(5).getValue();
						this.setMinValue(cc);
						}  	
					}
			},{
				text: '查找',
				handler:'logGridFind'
			}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{roleLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	})
	
});