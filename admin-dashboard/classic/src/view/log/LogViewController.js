Ext.define('Admin.view.log.LogViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.logViewController',
   
   
   logGridFind: function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var userName=Ext.getCmp('logCondition').items.getAt(1).getValue();
	   var operation=Ext.getCmp('logCondition').items.getAt(3).getValue();
	   var beginTime=null;
	   var endTime=null;
	   beginTime=Ext.getCmp('logCondition').items.getAt(5).getValue();

	   if(beginTime&&beginTime.length!=0){
			endTime=Ext.Date.add(Ext.getCmp('logCondition').items.getAt(7).getValue(), Ext.Date.DAY,1);
	   } else {
			endTime=Ext.getCmp('logCondition').items.getAt(7).getValue();
	   }
	   Ext.Ajax.request({ 
			url : 'log/findByCondition.json', 
			params : { 
                    userName:userName,
                    operation:operation,
					beginDate:Ext.util.Format.date(beginTime, 'Y/m/d H:i:s'),
					endDate:Ext.util.Format.date(endTime, 'Y/m/d H:i:s'),
					page:1,
					start:0,
					limit:15,
					sort:'createDate',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });
	  

   }
	
});