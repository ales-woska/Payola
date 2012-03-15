package cz.payola.common.model

trait Plugin extends NamedEntity
{
    /** Type of the parameters of the plugin */
    type ParameterType <: Parameter[_]

    protected var _parameters: Seq[ParameterType]

    def parameters = _parameters
}
