Ext.define('Admin.view.task.MyTaskGrid', {    //1.修改文件路径
      extend: 'Ext.grid.Panel',         //2.继承的组件类型
  //3.重写继承组件的属性：
  xtype: 'myTaskGrid',
  title:'<b>我的任务</b>',
  bind:'{activitiTaskLists}',
  selModel: Ext.create('Ext.selection.CheckboxModel'),
  columns: [
    // {text: 'taskId' ,sortable:false ,dataIndex:'taskId',hidden:true},
    // {text: 'createId' ,sortable:false ,dataIndex:'createId',hidden:true},
    // {text: 'userId' ,sortable:false ,dataIndex:'userId',hidden:true},
    // {text: '任务名称', sortable:false ,dataIndex:'taskName' ,width:150},
    // {text: '任务发布时间' ,sortable:true ,dataIndex:'createDate' ,width:200,
     // renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
    // {text: '任务完成时间' ,sortable:true ,dataIndex:'completeDate' ,width:200},
    // {text: '接收者', sortable:false ,dataIndex:'realName' ,width:120},
    // {text: '发布者', sortable:false ,dataIndex:'createName' ,width:120},
    // {text: '状态'  ,sortable:false ,dataIndex:'taskState'  ,flex:120},
    // {
      // xtype: 'actioncolumn',
      // text: '标记任务完成',
      // width: 100,
      // tdCls: 'action',
        // items: [{
          // icon:'resources/images/icons/complete.png',
          // tooltip: '标记完成',
          // handler: ('setStateComplete')
      // }]
    // },
	
	{text: 'id'  ,sortable:false ,dataIndex:'id', type: 'string', hidden:true ,width:120},
	{text: '委托人'  ,sortable:false ,dataIndex:'assignee'  ,type: 'string',width:120},
	{text: '创建时间'  ,sortable:false ,dataIndex:'createTime' , type: 'date',renderer:new Ext.util.Format.dateRenderer('Y-m-d') ,width:120},
	{text: '任务状态'  ,sortable:false ,dataIndex:'delegationState' ,type: 'string' ,width:120},
	{text: '描述'  ,sortable:false ,dataIndex:'description'  ,flex:1,type: 'string'},
	{text: '名字'  ,sortable:false ,dataIndex:'name' ,type: 'string' ,width:120},
	{text: '是否暂停'  ,sortable:false ,dataIndex:'suspended',type: 'string'  ,width:120},
	{text: '任务定义关键字'  ,sortable:false ,dataIndex:'taskDefinitionKey' ,type: 'string' ,width:120},
	{text: '发布者'  ,sortable:false ,dataIndex:'owner' ,type: 'string' ,width:120},
	{
		xtype: 'actioncolumn',
		width: 100,
		text: '表单填写',
		tdCls: 'action',
        items: [{
          icon:'resources/images/icons/complete.png',
          tooltip: '表单填写',
          handler: ('askToShowForm')
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

	tbar: Ext.create('Ext.Toolbar', {
			id: 'myTaskCondition',
				items:[{xtype:'tbtext',
					text:'发布者：'
				},{
					xtype:'textfield',
					width:90,
					itemsId:'createName'
					
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
							var cc = Ext.getCmp('myTaskCondition').items.getAt(5).getValue();
							this.setMinValue(cc);
							}  	
						}
				},{
					text: '查找',
					handler:'myTaskGridFind'
				}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{activitiTaskLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	})
	
});