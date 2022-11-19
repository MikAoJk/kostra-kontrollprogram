import React, {useEffect, useState} from "react"
import {kontrollerSkjemaAsync, listSkjemaTyperAsync} from "./api/apiCalls"
import {useFieldArray, useForm} from "react-hook-form"
import {KostraFormVm} from "./kostratypes/kostraFormVm"
import {KostraFormTypeVm} from "./kostratypes/kostraFormTypeVm"
import {Nullable} from "./kostratypes/nullable"
import {Button, Form} from "react-bootstrap"
import * as yup from "yup"
import {yupResolver} from "@hookform/resolvers/yup"

// @ts-ignore
import PlusCircle from "./assets/icon/plus-circle.svg"
// @ts-ignore
import DashCircle from "./assets/icon/dash-circle.svg"
// @ts-ignore
import IconKostra from "./assets/icon/ikon-kostra.svg"

const COMPANY_ID_REQUIRED_MSG = "Organisasjonsnummer er påkrevet"
const COMPANY_ID_REGEX_MSG = "Må starte med '8' eller '9' etterfulgt av 8 siffer"

const MEBIBYTE_50 = 52428800

function App() {

    const [loadError, setLoadError] = useState<string>()
    const [skjematyper, setSkjematyper] = useState<KostraFormTypeVm[]>([])
    const [valgtSkjematype, setValgtSkjematype] = useState<Nullable<KostraFormTypeVm>>()

    const validationSchema: yup.SchemaOf<KostraFormVm> = yup.object().shape({
            aar: yup.number().transform(value => (isNaN(value) ? 0 : value)).positive("Årgang er påkrevet"),
            region: yup.string().required("Region er påkrevet").matches(/^\d{6}$/, "Region må bestå av 6 siffer"),
            skjema: yup.string().required("Skjematype er påkrevet"),
            orgnrForetak: yup.string().when([], {
                is: () => valgtSkjematype?.labelOrgnr,
                then: yup.string()
                    .required(COMPANY_ID_REQUIRED_MSG)
                    .matches(/^[8|9]\d{8}$/i, COMPANY_ID_REGEX_MSG)
            }),
            orgnrVirksomhet: yup.array().of(
                yup.object().shape({
                    orgnr: yup.string()
                        .required(COMPANY_ID_REQUIRED_MSG)
                        .matches(/^[8|9]\d{8}$/i, COMPANY_ID_REGEX_MSG)
                })
            ),
            skjemaFil: yup.mixed()
                .test(
                    "required",
                    "Vennligst velg fil",
                    (files: FileList) => files?.length > 0
                ).test(
                    "size",
                    "Maks. filstørrelse er 50 MiB",
                    (files: FileList) => files?.[0]?.size < MEBIBYTE_50
                )
        }
    ).required()

    const {
        control,
        register,
        getValues,
        setValue,
        handleSubmit,
        formState: {errors, touchedFields},
        formState,
        watch
    } = useForm<KostraFormVm>({
        mode: "onBlur",
        resolver: yupResolver(validationSchema)
    })

    const {
        fields: orgnrVirksomhetFields,
        append: appendOrgnr,
        remove: removeOrgnr
    } = useFieldArray<KostraFormVm>({
        control,
        name: "orgnrVirksomhet"
    })

    const onSubmit = handleSubmit(data => {
        kontrollerSkjemaAsync(data).then(response => console.log(response))
    })

    useEffect(() => {
        listSkjemaTyperAsync()
            .then(skjematyper => {
                setSkjematyper(skjematyper)
                setLoadError("")
            })
            .catch(() => setLoadError("Lasting av skjematyper feilet"))
    }, [])

    useEffect(() => {
        if (skjematyper.length) {
            const subscription = watch((value, {name, type}) => {
                if (!(name == 'skjema' && type == 'change')) {
                    return;
                }

                const localValgtSkjema = skjematyper.find(it => it.id == value.skjema)
                setValgtSkjematype(localValgtSkjema)

                localValgtSkjema?.labelOrgnrVirksomhetene
                    ? appendOrgnr({orgnr: ""})
                    : orgnrVirksomhetFields.forEach((it, index) => {
                        // do mot remove braces, code will not be executed
                        removeOrgnr(index)
                    })
            })
            return () => subscription.unsubscribe()
        }
    }, [skjematyper, watch])

    return <Form noValidate validated={formState.isValid} onSubmit={onSubmit}>
        <div className="py-5 text-center">
            <h2 className="mb-3">
                <img src={IconKostra}
                     height="70px"
                     className="pe-4"
                     alt="Kostra"/>
                Kostra kontrollprogram
            </h2>

            {loadError && <span className="text-danger">{loadError}</span>}

            <hr className="my-0"/>
        </div>

        <div className="row g-3">
            { /** ÅRGANG */}
            <Form.Group
                className="col-sm-6"
                controlId="aar">
                <Form.Label>Årgang</Form.Label>
                <Form.Select
                    {...register("aar")}
                    isValid={touchedFields.aar && !errors.aar}
                    isInvalid={errors.aar != null}>
                    <option value="">Velg år</option>
                    <option value="2022">2022</option>
                    <option value="2021">2021</option>
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.aar?.message}</Form.Control.Feedback>
            </Form.Group>

            { /** REGION */}
            <Form.Group
                className="col-sm-6"
                controlId="region">
                <Form.Label>Regionsnummer</Form.Label>
                <Form.Control
                    {...register("region")}
                    isValid={touchedFields.region && !errors.region}
                    isInvalid={errors.region?.type != null}
                    type="text"
                    autoComplete="off"
                    maxLength={6}
                    placeholder="6 siffer"/>
                <Form.Control.Feedback type="invalid">{errors.region?.message}</Form.Control.Feedback>
            </Form.Group>

            { /** SKJEMATYPE */}
            {skjematyper.length > 0 && <Form.Group
                className="col-sm-12"
                controlId="skjema">
                <Form.Label>Skjema</Form.Label>
                <Form.Select
                    {...register("skjema")}
                    isValid={touchedFields.skjema && !errors.skjema}
                    isInvalid={errors.skjema != null}>
                    <option value="">Velg skjematype</option>
                    {skjematyper.map(skjematype =>
                        <option key={skjematype.id}
                                value={skjematype.id}>{skjematype.tittel}</option>)}
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.skjema?.message}</Form.Control.Feedback>
            </Form.Group>}

            { /** ORGNR */}
            {valgtSkjematype?.labelOrgnr &&
                <Form.Group className="col-sm-6" controlId="orgnrForetak">
                    <Form.Label>{valgtSkjematype.labelOrgnr}</Form.Label>
                    <Form.Control
                        {...register("orgnrForetak")}
                        isValid={touchedFields.orgnrForetak && !errors.orgnrForetak}
                        isInvalid={errors.orgnrForetak?.type != null}
                        type="text"
                        autoComplete="off"
                        maxLength={9}
                        placeholder="9 siffer"/>
                    <Form.Control.Feedback type="invalid">{errors.orgnrForetak?.message}</Form.Control.Feedback>
                </Form.Group>}

            { /** ORGNR 2 */}
            {orgnrVirksomhetFields.length > 0 && <div className="col-sm-6">
                <div className="container ps-0">
                    {orgnrVirksomhetFields.map((item, index) => {
                        return <div key={item.id} className={index < 1 ? "row" : "row mt-2"}>
                            <Form.Group className="col-sm-10">
                                {index < 1 && <Form.Label>{valgtSkjematype?.labelOrgnrVirksomhetene}</Form.Label>}
                                <Form.Control
                                    {...register(`orgnrVirksomhet.${index}.orgnr`)}
                                    isValid={(touchedFields.orgnrVirksomhet as boolean[])?.[index]
                                        && !errors.orgnrVirksomhet?.[index]}
                                    isInvalid={errors.orgnrVirksomhet?.[index] != null}
                                    type="text"
                                    maxLength={9}
                                    placeholder="9 siffer"/>
                                <Form.Control.Feedback type="invalid">
                                    {errors.orgnrVirksomhet?.[index]?.orgnr?.message}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <div className="col-sm-2 mt-auto m-0 mb-2">
                                {index > 0 && <img
                                    onClick={() => removeOrgnr(index)}
                                    src={DashCircle}
                                    title="Fjern virksomhetsnummer"
                                    alt="Fjern virksomhetsnummer"/>}

                                {orgnrVirksomhetFields.length < 21
                                    && index == orgnrVirksomhetFields.length - 1
                                    && !errors.orgnrVirksomhet?.[index]
                                    && (touchedFields.orgnrVirksomhet as boolean[])?.[index]
                                    && <img
                                        className={index < 1 ? "ps-4" : "ps-1"}
                                        onClick={() => appendOrgnr({orgnr: ""})}
                                        src={PlusCircle}
                                        title="Legg til virksomhetsnummer"
                                        alt="Legg til virksomhetsnummer"/>}
                            </div>
                        </div>
                    })}
                </div>
            </div>}

            { /** FILE UPLOAD */}
            <Form.Group
                className="col-sm-12 mt-4"
                controlId="filnavn">
                <Form.Control
                    {...register("skjemaFil")}
                    isValid={touchedFields.skjemaFil && !errors.skjemaFil}
                    isInvalid={errors.skjemaFil?.type != null}
                    type="file"
                    accept=".dat,.xml"
                />
                <Form.Control.Feedback type="invalid">{errors.skjemaFil?.message}</Form.Control.Feedback>
            </Form.Group>

            <hr className="my-4"/>

            <Button
                type="submit"
                className="btn-secondary"
                disabled={!formState.isValid}>Kontroller fil</Button>

            <Button
                className="btn-secondary mt-5"
                onClick={() => {
                    setValue("aar", 2022)
                    setValue("region", "030100")
                    setValue("skjema", "0X")
                    setValue("orgnrForetak", "999999999")

                    setValgtSkjematype(skjematyper.find(it => it.id == getValues("skjema")))
                    appendOrgnr({orgnr: ""})
                }}
            >Sett testverdier 0X</Button>
        </div>
    </Form>
}

export default App