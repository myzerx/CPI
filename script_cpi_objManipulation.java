import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {
	def bodyVariable = message.getBody(String)
	def jsonFile = new JsonSlurper()
	def jsonObject = jsonFile.parseText(bodyVariable)
	def property = message.getProperties()
	def pReturnHomeOffice = property.get("pReturnHomeOffice")
	def nomeFuncionario = '', matriculaFuncionario = '', emailFuncionario = '', celularFuncionario = '', nivelFuncionario = '', nomeGestor = '', matriculaGestor = '', negocioFuncionario = '', diretoriaFuncionario = '', empresaFuncionario = '', areasFuncionario = '', filialFuncionario = '', Modelo_de_Trabalho = '', E_GestorFuncionario = '', E_GestorSize = 0, arrayEGestor = [], gestor = '', gestorID = '', arrayJsonResult = [], jsonData, logoEmpresaFuncionario = ''
	scriptWithoutObject(message, jsonObject, pReturnHomeOffice, arrayJsonResult)
	return message;
}


def scriptWithoutObject(message, jsonObject, pReturnHomeOffice, arrayJsonResult) {
	if (jsonObject.PerPerson.PerPerson.personalInfoNav == '') {
		nomeFuncionario
		matriculaFuncionario
	} else {
		PerPersonal = jsonObject.PerPerson.PerPerson.personalInfoNav.PerPersonal
		if (PerPersonal.getClass() == java.util.ArrayList) {
			nomeFuncionario = PerPersonal.displayName
			matriculaFuncionario = PerPersonal.personIdExternal
		} else {
			nomeFuncionario = PerPersonal.displayName
			matriculaFuncionario = PerPersonal.personIdExternal
		}
	}

	if (jsonObject.PerPerson.PerPerson.emailNav == '') {
		emailFuncionario = jsonObject.PerPerson.PerPerson.find {
			element -> element = jsonObject.PerPerson.PerPerson.emailNav
		}

		if (emailFuncionario == null) {
			emailFuncionario = ''
		}
	} else {
		PerEmail = jsonObject.PerPerson.PerPerson.emailNav.PerEmail
		if (PerEmail.getClass() == java.util.ArrayList) {
			if (PerEmail.emailType[0] == '59895' && PerEmail.emailType[0] != '59894') {
				emailFuncionario = PerEmail.emailAddress[0]
			} else {
				emailFuncionario = PerEmail.emailAddress[1]
			}
		} else {
			if (PerEmail.emailType == '59895' && PerEmail.emailType != '59894') {
				emailFuncionario = PerEmail.emailAddress
			} else {
				emailFuncionario = PerEmail.emailAddress
			}
		}
	}

	if (jsonObject.PerPerson.PerPerson.phoneNav == '') {
		celularFuncionario = jsonObject.PerPerson.PerPerson.find {
			element -> element = jsonObject.PerPerson.PerPerson.phoneNav
		}

		if (celularFuncionario == null) {
			celularFuncionario = ''
		}
	} else {
		PerPhone = jsonObject.PerPerson.PerPerson.phoneNav.PerPhone
		if (PerPhone.getClass() == java.util.ArrayList) {
			if (PerPhone.phoneType[0] == "59904" && PerPhone.phoneType[0] != "59906") {
				celularFuncionario = PerPhone.phoneNumber[0]

			} else {
				celularFuncionario = PerPhone.phoneNumber[1]

			}
		} else {
			if (PerPhone.phoneType[0] == "59904" && PerPhone.phoneType != "59906") {
				celularFuncionario = PerPhone.phoneNumber

			} else {
				celularFuncionario = PerPhone.phoneNumber

			}
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav == '') {
		empresaFuncionario
	} else {
		EmpJob = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob
		if (EmpJob.getClass() == java.util.ArrayList) {
			empresaFuncionario = EmpJob.company
		} else {
			empresaFuncionario = EmpJob.company
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav == '') {
		nivelFuncionario
	} else {
		EmpJob = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.customString18Nav.cust_employeeClassBenefit
		if (EmpJob.getClass() == java.util.ArrayList) {
			nivelFuncionario = EmpJob.externalName
		} else {
			nivelFuncionario = EmpJob.externalName
		}
	}

	if (jsonObject.PerPerson.PerPerson.userAccountNav.UserAccount.user == '') {
		nomeGestor = 'Não possui Gestor'
		matriculaGestor = 'Não possui Gestor'
	} else {
		manager = jsonObject.PerPerson.PerPerson.userAccountNav.UserAccount.user.User.manager
		if (manager == '') {
			nomeGestor = 'Não possui Gestor'
			matriculaGestor = 'Não possui Gestor'
		} else {
			managerUser = jsonObject.PerPerson.PerPerson.userAccountNav.UserAccount.user.User.manager.User
			if (managerUser.getClass() == java.util.ArrayList) {
				nomeGestor = managerUser.defaultFullName
				matriculaGestor = managerUser.userId
			} else {
				nomeGestor = managerUser.defaultFullName
				matriculaGestor = managerUser.userId
			}
		}

	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.businessUnitNav == '') {
		negocioFuncionario
	} else {
		FoBusinessUnit = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.businessUnitNav.FOBusinessUnit
		if (FoBusinessUnit.getClass() == java.util.ArrayList) {
			negocioFuncionario = FoBusinessUnit.name
		} else {
			negocioFuncionario = FoBusinessUnit.name
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav == '') {
		diretoriaFuncionario
	} else {
		EmpJob = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob
		if (EmpJob.getClass() == java.util.ArrayList) {
			diretoriaFuncionario = EmpJob.division
		} else {
			diretoriaFuncionario = EmpJob.division
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.departmentNav == '') {
		areasFuncionario
	} else {
		FODepartment = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.departmentNav.FODepartment
		if (FODepartment.getClass() == java.util.ArrayList) {
			areasFuncionario = FODepartment.name
		} else {
			areasFuncionario = FODepartment.name
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.locationNav == '') {
		filialFuncionario
	} else {
		FOLocation = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob.locationNav.FOLocation
		if (FOLocation.getClass() == java.util.ArrayList) {
			filialFuncionario = FOLocation.name
		} else {
			filialFuncionario = FOLocation.name
		}
	}

	if (jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav == '') {
		E_GestorFuncionario
	} else {
		EmpJobSwitch = jsonObject.PerPerson.PerPerson.employmentNav.EmpEmployment.jobInfoNav.EmpJob
		if (EmpJobSwitch.getClass() == java.util.ArrayList) {
			E_GestorFuncionario = EmpJobSwitch.customString18
			switch (E_GestorFuncionario){
                case 'G2': case 'G5': case 'J2': case 'J3': case 'J4': case 'J5': case 'J6': case 'P2':  case 'P3': case 'P4': 
                case 'P5': case 'P6': case 'P9': case 'PE': case 'PM': case 'PN': case 'PR': case 'PS':  case 'PX': case 'PY':
                EGestorReturn = 'Sim'
                break;

                default:
                EGestorReturn = 'Não'
                break;
            }
			
 
		} else {
            E_GestorFuncionario = EmpJobSwitch.customString18
			switch (E_GestorFuncionario){
                case 'G2': case 'G5': case 'J2': case 'J3': case 'J4': case 'J5': case 'J6': case 'P2':  case 'P3': case 'P4': 
                case 'P5': case 'P6': case 'P9': case 'PE': case 'PM': case 'PN': case 'PR': case 'PS':  case 'PX': case 'PY':
                EGestorReturn = 'Sim'
                break;

                default:
                EGestorReturn = 'Não'
                break;
            }
		}
	}

	switch (empresaFuncionario) {
        //Uol Cases
		case 'UOLB': case 'INGR': case 'NETP':
			logoEmpresaFuncionario = 'https://ucarecdn.com/2a4c1696-15fc-4c08-98b6-f3262726fc00/positivo.png'
			break;
        //PagSeguro Cases
		case 'TETD': case 'CRED': case 'BANS': case 'CONC': case 'BOAC': case 'R2TC': case 'TILI': case 'MOIP': case 'YAMI': case 'ZYGO':
			logoEmpresaFuncionario = 'https://ucarecdn.com/f9f47914-94ef-49c7-b878-78b560d3b262/PagBankPagSeguro.png'
			break;
        //Edtech Cases
		case 'UOLC': case 'CIAT':
			logoEmpresaFuncionario = 'https://ucarecdn.com/143054c4-0f20-4fac-a793-e3f1a1a101c0/LogoEdTech.png'
			break;
        //Edge Uol Cases
		case 'Edge UOL':
			logoEmpresaFuncionario = 'https://ucarecdn.com/b64ee8df-3d50-418a-ad52-81d92b24e32f/edge_cor_hor_positivo.png'
			break;
	}

	jsonFrom = JsonOutput.toJson(
		'name': nomeFuncionario,
		'email': 'lse_rcassola@uolinc.com', //emailFuncionario,
		'phone': '(15) 99775-7694' //celularFuncionario

	)

	jsonData = JsonOutput.toJson(
		'empresa': empresaFuncionario,
		'logo_empresa': logoEmpresaFuncionario,
		'matricula': matriculaFuncionario,
		'nivel': nivelFuncionario,
		'nome_do_gestor': nomeGestor,
		'matricula_do_gestor': matriculaGestor,
		'negocio': negocioFuncionario,
		'diretoria': diretoriaFuncionario,
		'areas': areasFuncionario,
		'modelo_de_trabalho': pReturnHomeOffice,
		'e_gestor': EGestorReturn,
		'filial': filialFuncionario,
		'ambiente': 'teste'
	)

	arrayJsonResult.push(jsonFrom)
	arrayJsonResult.push(jsonData)
	message.setProperty('pJsonFrom', jsonFrom)
	message.setProperty('pJsonData', jsonData)
	message.setBody(JsonOutput.prettyPrint(arrayJsonResult.toString()))

}
