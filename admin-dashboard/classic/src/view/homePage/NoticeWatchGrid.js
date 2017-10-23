Ext.define('Admin.view.homePage.NoticeWatchGrid', {    //1.修改文件路径
      extend: 'Ext.grid.Panel',         //2.继承的组件类型
  //3.重写继承组件的属性：
    xtype: 'noticeWatchGrid',
  bind:'{noticeWatchLists}',
  id:'noticeWatchGrid',
  listeners:{
    cellclick:function(grid,td, cellIndex, record, tr, rowIndex){
      //var record = this.getStore().getAt(rowIndex); 
        var orderWindow = Ext.widget('orderWindow',{
        title:'查看公告',
        html:'<h2 align="center">'+grid.getStore().getAt(rowIndex).data.noticeName+'</h2>'+'<p>'+grid.getStore().getAt(rowIndex).data.noticeText+'</p>'
      });
    }
  },
  columns: [
    {sortable:true ,dataIndex:'noticeId',hidden:true},
    {dataIndex:'noticeName' ,flex:1},
    {sortable:true ,dataIndex:'noticeTime'  ,width:150
      ,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},

  ],  


});