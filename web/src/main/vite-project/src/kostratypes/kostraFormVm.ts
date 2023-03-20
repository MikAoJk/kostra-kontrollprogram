import CompanyIdVm from "./companyIdVm"
import Nullable from "./nullable";

interface KostraFormVm {
    aar: NonNullable<number>
    skjema: NonNullable<string>
    region: NonNullable<string>
    orgnrForetak: Nullable<string>
    orgnrVirksomhet: CompanyIdVm[] | undefined
    skjemaFil: FileList
}

export default KostraFormVm