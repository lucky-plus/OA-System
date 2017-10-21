Ext.define('Admin.view.assets.AdvancedVType', {
    override: 'Ext.form.field.VTypes',

    daterange: function(val, field) {
        var date = field.parseDate(val);

        if (!date) {
            return false;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = field.up('form').down('#' + field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        }
        else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = field.up('form').down('#' + field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    },

    daterangeText: 'Start date must be less than end date',

    password: function(val, field) {
        if (field.initialPassField) {
            var pwd = field.up('form').down('#' + field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },

    passwordText: '密码不一致',


	price: function (val, field) {
		var price = field.parsePrice(val);

		if (field.startPriceField) {
            var start = field.up('form').down('#' + field.startPriceField);
            start.setMaxValue(price);
            start.valiprice();
            this.priceRangeMax = price;
        }
        else if (field.endPriceField) {
            var end = field.up('form').down('#' + field.endPriceField);
            end.setMinValue(price);
            end.valiprice();
            this.priceRangeMin = price;
        }
        return true;
	},

	priceText: 'Start price must be less than end price',

});