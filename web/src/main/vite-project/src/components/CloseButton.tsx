import {Button} from "react-bootstrap";

const CHAR_MULTIPLICATION_X = "\u2715"

export const CloseButton = ({onClick}: { onClick: () => void }) =>
    <Button
        onClick={onClick}
        className="bg-transparent btn-outline-light ms-1 p-0 ps-1 pe-1 rounded-circle text-black-50"
        title="Slett rapport">
        {CHAR_MULTIPLICATION_X}
    </Button>