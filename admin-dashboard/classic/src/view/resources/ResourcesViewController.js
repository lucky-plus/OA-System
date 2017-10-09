Ext.define('Admin.view.resources.ResourcesViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.resourcesViewController',

    fileUpload: function(btn) {
	var resourcesForm = btn.up('form').getForm();
	resourcesForm.submit({  
			url : 'resources/upload', 
			method : 'post', 
			//enctype="multipart/form-dat",
	   })
    },
	
});