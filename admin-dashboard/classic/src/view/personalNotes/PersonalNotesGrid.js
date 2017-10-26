Ext.define('Admin.view.personalNotes.PersonalNotesGrid', {    //1.修改文件路径
      extend: 'Ext.grid.Panel',         //2.继承的组件类型
  //3.重写继承组件的属性：
    xtype: 'personalNotesGrid',
  title:'<b>人事记录</b>',
  bind:'{personalNotesLists}',
  id:'personalNotesGrid',
  columns: [
    {text: '记录编号'       ,sortable:true ,dataIndex:'notesId',hidden:true},
	{text: '申请人' , dataIndex:'realName'    ,width:150},
    {text: '申请时间'  ,sortable:true ,dataIndex:'examineTime'  ,width:150
      ,renderer: Ext.util.Format.dateRenderer('Y/m/d')},
	{text: '申请流程' , dataIndex:'notesName'    ,width:150},
	{text: '申请结果' , dataIndex:'examineResult'    ,width:150}

  ],  





  
  
  bbar: Ext.create('Ext.PagingToolbar', {
    bind:'{personalNotesLists}',
    displayInfo: true,
    displayMsg: '第 {0} - {1}条， 共 {2}条',
    emptyMsg: "No topics to display",
  }),
  
  

});