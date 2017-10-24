Ext.define('Admin.view.business.ContractWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.contractWindow',
    autoShow: true,
    modal: true,
	formWin: false,
    layout: 'fit',
	formParamNum:1,
    scrollable: true,
    width: 200,
    height: 200,

    afterRender: function () {
        var me = this;

        me.callParent(arguments);

        me.syncSize();

        // Since we want to always be a %age of the viewport, we have to watch for
        // resize events.
        Ext.on(me.resizeListeners = {
            resize: me.onViewportResize,
            scope: me,
            buffer: 50
        });
    },

    doDestroy: function () {
        Ext.un(this.resizeListeners);

        this.callParent();
    },

    onViewportResize: function () {
        this.syncSize();
    },

    syncSize: function () {
        var width = Ext.Element.getViewportWidth(),
            height = Ext.Element.getViewportHeight();

        // We use percentage sizes so we'll never overflow the screen (potentially
        // clipping buttons and locking the user in to the dialog).
		if(!this.formWin){
			this.setSize(Math.floor(width * 0.9), Math.floor(height * 0.9));
			this.setXY([ Math.floor(width * 0.05), Math.floor(height * 0.05) ]);
		}else{
			var fWidth = 400; 
			var formFitHeight = this.formParamNum * 65;
			fHeight = formFitHeight<700?formFitHeight:700<Math.floor(width * 0.9)?700:Math.floor(width * 0.9);
			alert(fHeight);
			this.setSize(Math.floor(fWidth), Math.floor(fHeight));
			this.setXY([ Math.floor((width-fWidth)*0.5), Math.floor((height-fHeight)*0.5) ]);
		}
    }
});
