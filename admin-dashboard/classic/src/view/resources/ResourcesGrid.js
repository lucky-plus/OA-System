Ext.define('Admin.view.resources.ResourcesGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'resourcesGrid',
	title:'<b>资料中心</b>',
	bind:'{resourcesLists}',
	id:'resourcesGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: '资料编号',dataIndex:'resId',hidden:true},
        {text: '资料名称' ,dataIndex:'resName' ,flex:1},
		{text: '发布时间'  ,sortable:true ,dataIndex:'resDate'  ,width:150
			,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '发布者',dataIndex:'userName'    ,width:150},
		{xtype: 'actioncolumn',  text: '操作' ,width:150,tdCls: 'action',  
            items: ['-',{  
				icon:'resources/images/icons/dowanload.png',
                tooltip: '下载',
                handler: 'resourcesGridDownloadOne'  
				},'-',{  

				icon:'resources/images/icons/editor.png',
                tooltip: '编辑',
				handler:function(){
					var cfg = Ext.apply({
					xtype:'orderWindow'
					},{
						title:'公告',
						items:[Ext.apply({xtype:'resourcesForm'})]
					});
					Ext.create(cfg);
				}
                
               // handler: function (grid, rowIndex, colIndex, node, e, record, rowEl) {    }  
            },'-', {  
				icon:'resources/images/icons/delete.png',
                tooltip: '删除',
                handler: 'resourcesGridDeleteOne'
            }]  }
			

	],		

	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
			text: '上传',
			iconCls:'x-fa fa-plus',
			ui:'soft-blue',
			handler:function(){
					var cfg = Ext.apply({
					xtype:'orderWindow'
					},{
						title:'资料上传',
						items:[Ext.apply({xtype:'resourcesForm'})]
					});
					Ext.create(cfg);
				}
		},'-', {
			text: '批量下载',
			iconCls:'x-fa fa-arrow-circle-o-down',
			handler: 'resourcesGridDownloadMany'
		},'-', {
			text: '批量删除',
			iconCls:'x-fa fa-trash',
			handler: 'resourcesGridDelete'
		},'-',{xtype:'tbtext',
				text:'标题：'
			},{
				xtype:'textfield',
				width:300
			},{xtype:'tbtext',
				text:'时间：'
			},{
				xtype:'datefield',
			},{xtype:'tbtext',
				text:'至：'
			},{
				xtype:'datefield',
			},{
				text:'查找'
			}]
	}),
	
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{resourcesLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});