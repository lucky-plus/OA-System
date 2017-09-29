Ext.define('Admin.view.notice.NoticeGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'noticeGrid',
	title:'<b>公告列表</b>',
	bind:'{noticeLists}',
	id:'noticeGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: '公告编号'			  ,sortable:true ,dataIndex:'noticeId',hidden:true},
        {text: '标题' ,dataIndex:'noticeName' ,flex:1 ,
			listeners:{
				click:function(){
				var cfg = Ext.apply({
				xtype:'orderWindow'
				},{
					title:'公告',
					items:[Ext.apply({xtype:'noticeText'})]
				});
				Ext.create(cfg);
			}
		}
		
		},
		{text: '发布时间'  ,sortable:true ,dataIndex:'noticeTime'  ,width:150
			,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '发布者',dataIndex:'userName'    ,width:150},
		{xtype: 'actioncolumn',  text: '操作' ,width:100,tdCls: 'action',  
            items: ['-',{  

				icon:'resources/images/icons/editor.png',
                tooltip: '编辑',
				handler: ('noticeGridOpenEditWindow')
				
            },'-', {  
				icon:'resources/images/icons/delete.png',
                tooltip: '删除',
                handler: ('noticeGridDeleteOne') 
            }]  }

	],	





	tbar: Ext.create('Ext.Toolbar', {
			id: 'xiaotingzi2' ,
			items:[ {
				text: '新增',
				iconCls:'x-fa fa-plus',
				ui:'soft-blue',
				handler: 'noticeGridAdd'
			},'-', {
				text: '删除',
				iconCls:'x-fa fa-trash',
				handler: 'noticeGridDeleteDate'
			},'-',{xtype:'tbtext',
				text:'标题：'
			},{
				xtype:'textfield',
				width:300
			},{xtype:'tbtext',
				text:'时间：'
			},{
				 xtype:'datefield',  
                    itemId:'beginDate',  
                    format:'Y-m-d',  
					
			
			},{xtype:'tbtext',
				text:'至：'
			},{
				xtype:'datefield',  
                    itemId:'endDate',  
                    format:'Y-m-d',  
					listeners: {  
					focus: function(){
						var cc = Ext.getCmp('xiaotingzi2').items.getAt(7).getValue();
						this.setMinValue(cc);
						}  	
					}
			},{
				text: '查找',
				handler: 'noticeGridAdd'
			}
			]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{orderLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	})
	
});