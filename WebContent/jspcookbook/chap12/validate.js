<script language="JavaScript"> 
function validate(form1)
{

    for (i = 0; i < form1.length; i++){

       if( (form1.elements[i].value == "")  ){
           alert("You must provide a value for the field named: " + form1.elements[i].name)
           return false
           }
    
	}
	return true

}
</script>
