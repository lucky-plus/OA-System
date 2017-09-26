Ext.define('Admin.view.notice.NoticeGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'NoticeGrid',
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
					items:[Ext.apply({xtype:'noticeCompose'})]
				});
				Ext.create(cfg);
			}
		}
		
		},
		{text: '发布时间'  ,sortable:true ,dataIndex:'noticeTime'  ,width:150
			,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '发布者',dataIndex:'noticeAuthor'    ,width:150},
		{xtype: 'actioncolumn',  text: '操作' ,width:100,tdCls: 'action',  
            items: ['-',{  

				icon:'resources/images/icons/editor.png',
                tooltip: '编辑',
				handler:function(){
					var cfg = Ext.apply({
					xtype:'orderWindow'
					},{
						title:'公告',
						items:[Ext.apply({xtype:'noticeCompose'})]
					});
					Ext.create(cfg);
				}
                
               // handler: function (grid, rowIndex, colIndex, node, e, record, rowEl) {    }  
            },'-', {  
				icon:'resources/images/icons/delete.png',
                tooltip: '删除',
              //  handler: function (grid, rowIndex, colIndex, node, e, record, rowEl) {   }  
            }]  }

	],	





	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
				text: '新增',
				iconCls:'x-fa fa-plus',
				ui:'soft-blue',
				handler: 'noticeGridAdd'
			},'-', {
				text: '删除',
				iconCls:'x-fa fa-trash',
				handler: 'orderGridDelete'
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

Ext.define('Admin.model.notcie.NoticeModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'noticeId'			,type: 'int'},
        {name:'noticeName' ,type: 'string'},
        {name:'noticeTime'	,type: 'date'},
		{name:'noticeAuthor',type: 'string'}
    ]
});