Ext.define('Admin.view.task.ReleaseTaskGrid', {    //1.修改文件路径
      extend: 'Ext.grid.Panel',         //2.继承的组件类型
  //3.重写继承组件的属性：
  xtype: 'releaseTaskGrid',
  id:'releaseTaskGrid',
  title:'<b>发布任务</b>',
  bind:'{taskLists}',
  listeners:{
    cellclick:function(btn,td, cellIndex, record, tr, rowIndex){
		btn.up('panel').task = rowIndex;
		btn.up('panel').taskFind = record;
		// Ext.getCmp('postGrid').getStore().load({params:{deptId : record.get('id')}});
	}
  },
  selModel: Ext.create('Ext.selection.CheckboxModel'),
  columns: [
    {text: 'taskId' ,sortable:false ,dataIndex:'taskId',hidden:true},
    {text: 'createId' ,sortable:false ,dataIndex:'createId',hidden:true},
    {text: 'userId' ,sortable:false ,dataIndex:'userId',hidden:true},
    {text: '任务名称', sortable:false ,dataIndex:'taskName' ,width:150},
    {text: '任务发布时间' ,sortable:true ,dataIndex:'createDate' ,width:200,
     renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
    {text: '任务完成时间' ,sortable:true ,dataIndex:'completeDate' ,width:200},
    {text: '接收者', sortable:false ,dataIndex:'realName' ,width:120},
    {text: '发布者', sortable:false ,dataIndex:'createName' ,width:120},
    {text: '状态'  ,sortable:false ,dataIndex:'taskState'  ,flex:120},
    {
      xtype: 'actioncolumn',
      text: '终止任务',
      width: 100,
      tdCls: 'action', 
        items: [{
          icon:'resources/images/icons/delete2.png',
          tooltip: '终止任务',
          handler: ('setStateStop')
      }]
    },
    {
      xtype: 'actioncolumn',
      text: '查看详情',
      width: 100,
      tdCls: 'action',
        items: [{
          icon:'resources/images/icons/search.png',
          tooltip: '查看详情',
          handler: ('showTaskText')
      }]
    }
  ], 
  dockedItems:[
		Ext.create('Ext.Toolbar', {
				items:[{
					text: '发布任务',
					id: 'taskAddButton',
					iconCls:'x-fa fa-plus',
					ui:'soft-blue',
					//handler: 'roleGridAdd'
					listeners:{
						click:'taskGridAdd'
					}
				},'-', {
					text: '修改',
					id: 'taskUpdateButton',
					iconCls:'x-fa fa-edit',
					handler: 'taskGridEdit'
				},'-', {
					text: '删除',
					id: 'taskDeleteButton',
					iconCls:'x-fa fa-trash',
					handler: 'taskGridDelete'
				}]
		}),
		Ext.create('Ext.Toolbar', {
			id: 'releaseTaskCondition',
			items:[{
				xtype:'tbtext',
				text:'接收者：'
			},{
				xtype:'textfield',
				width:90,
				itemsId:'realName'
				
			},{xtype:'tbtext',
                text:'状态:'
            },{
				xtype: 'combobox',
				name:'taskState',
      			width:100,
				store:  Ext.create('Ext.data.Store', {
					fields: ['value', 'name'],
					data : [
						{"value":"未完成", 	"name":"未完成"},
						{"value":"已完成",  "name":"已完成"},
						{"value":"已终止", 	"name":"已终止"}
					]
				}),
				queryMode: 	  'local',
				displayField: 'name',
				valueField:   'value'
			},
			{xtype:'tbtext',
				text:'发布时间：'
			},{
				 xtype:'datefield',  
       			 width:125,
			 		editable:false,
                    itemId:'beginDate',  
                    format:'Y-m-d',  
					value:'2017-01-01'
					
			
			},{xtype:'tbtext',
				text:'至：'
			},{
				xtype:'datefield',  
       			width:125,
			 		editable:false,  
                    itemId:'endDate',  
                    format:'Y-m-d',  
					value:new Date(),
					listeners: {  
					focus: function(){
						var cc = Ext.getCmp('releaseTaskCondition').items.getAt(5).getValue();
						this.setMinValue(cc);
						}  	
					}
			},{
				text: '查找',
				handler:'releaseTaskGridFind'
			}]
		})
	],
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{roleLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	})
	
});