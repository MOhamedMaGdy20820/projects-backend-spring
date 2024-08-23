import { FormControl, ValidationErrors } from "@angular/forms";

export class MOMGValidators {
      // whitespace validation
      static notOnlyWhitespace(control: FormControl) : ValidationErrors | null{
        
        // check if string only contains whitespace
        if ((control.value != null) && (control.value.trim().length === 0)) {

            // invalid, return error object
            return { 'notOnlyWhitespace': true };  // error key make it = true
        }
            // valid, return null
            return null;
    }


}
