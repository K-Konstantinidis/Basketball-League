const dropdown = document.querySelector('#teamCity_dropdown');
const newFields = document.querySelectorAll('.newCity');
const textFields = document.querySelectorAll('input[type="text"]');
const imgField = document.querySelector('input[type="file"]');

const fields = [...textFields, imgField];

function grayOut(){
	if(dropdown.selectedIndex !=0 ){
		newFields.forEach((field)=>{
			field.setAttribute('disabled', '');
		})
	}else{
		newFields.forEach((field)=>{
			field.removeAttribute('disabled');
		})
	}
}

function lockOut() {
	if(!dropdown.hasAttribute('disabled')) dropdown.setAttribute('disabled', '');
}

function clearForm(){
	fields.forEach((input)=>{
		input.value = "";
	});
	dropdown.selectedIndex = "0";
	dropdown.removeAttribute('disabled');
	grayOut();
}

newFields.forEach((field)=>{
		field.addEventListener("input", lockOut)
	});
